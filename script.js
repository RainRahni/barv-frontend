window.addEventListener('load', () => {
    const datas = getExistingNextMealNamesFromDatabase(getNextMealTime())
    .then((resolvedValue) => {
        return Object.entries(resolvedValue);
    });
    createAnchorElementForMealDropdown(datas);
});



let editPencilClicked = false;

let currentMeal;

const clearTableAndDisplayMealFoods = (specificMealName) => {
    eraseAllRowsFromScreen();
    mealTime = specificMealName.split("-")[0];
    const meal = getMealWithNameFromDatabase(specificMealName)
    .then((resolvedValue) => {
        currentMeal = resolvedValue;
        return Object.entries(resolvedValue)[7][1];
    });
    resetTotalMacrosAndCalories();
    generateRows(meal);
    return meal;
}

const getCurrentWeekDay = () => {
    const weekdays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
    const d = new Date();
    return weekdays[d.getDay()].toUpperCase();
};

document.getElementById("weekday").innerHTML = getCurrentWeekDay();

const getCurrentTime = () =>{
    const timeRightNow = new Date();
    let hours = timeRightNow.getHours()
    if (13 >= hours && hours > 8){
        return "13:00";
    }else if (18 >= hours && hours > 13){
        return "18:00";
    }else if (hours > 18 || hours <= 8 ){
        return "8:20";
    }
};

const getNextMealTime = () => {
    if (getCurrentTime() == "8:20"){
        return "BREAKFAST";
    }else if (getCurrentTime() == "13:00"){
        return "LUNCH";
    }else{
        return "DINNER";
    }   
};

document.getElementById("nextmealdisplay").innerHTML = getNextMealTime();

let popup = document.getElementById("popup");
function openAddFoodPopup() {
    popup.classList.add("open-popup");
}

const mealData = [];

function closeAddFoodPopup() {
    popup.classList.remove("open-popup");
}

//Both visually and functionally
const addFoodToMeal = () => {
    const inputs = document.querySelectorAll(".input");
    const data = getInputValuesFromFood(inputs);
    const entries = Object.entries(data);
    generateSingleRow(entries);
    mealData.push(data);
}

const eraseAllRowsFromScreen = () => {
    const table = document.getElementById("tableFoods");
    table.innerHTML = "";
}

const requireEveryInput = () => {
    let inputsHaveValues = checkInputsCorrectValues();
    if (inputsHaveValues) {
        closeAddFoodPopup();
        addFoodToMeal();
    }
}
const saveOrUpdateMealWhetherEditClicked = () => {
    removeOnlyDeleteButtons();
    deleteCheckmarkAndAddButton();
    const meal = constructMeal(); 
    if (editPencilClicked) {
        changeEditButtonColor(false);
        updateMealInDatabase(meal, currentMeal);
        editPencilClicked = false;
    } else {
        saveMealToDatabase(meal, "meal/addMeal")
    }
}

const updateMealInDatabase = (newMeal, currentMeal) => {
    return fetch(`http://localhost:8080/api/v1/meal/updateMeal`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({currentMeal, newMeal})
    });
}

const constructMeal = () => {
    let meal = {};
    let nameOfMeal = mealTime + "-" + totalCalories;
    Object.assign(meal, {name : nameOfMeal});
    Object.assign(meal, {calories : totalCalories});
    Object.assign(meal, {protein : totalProtein});
    Object.assign(meal, {carbohydrates : totalCarbs});
    Object.assign(meal, {fats : totalFats});
    Object.assign(meal, {type : mealTime.toUpperCase()});
    Object.assign(meal, {foods : mealData});
    return meal;
}

const changeEditButtonColor = (wantsToEdit) => {
    const editButton = document.getElementById("editPencil");
    if (wantsToEdit) {
        editButton.style.backgroundColor = "white";
    } else {
        editButton.style.backgroundColor = "grey";
    }
} 
//Add and remove meal popup from screen
let mealPopup = document.getElementById("mealPopup");
function openMealPopup() {
    console.log(mealPopup);
    mealPopup.classList.add("open-popup"); 
}
let mealTime = "";
//Get meal time from user and assign it.
function chosenMeal(meal) {
    mealPopup.classList.remove("open-popup");
    mealTime = meal;
    eraseAllRowsFromScreen();
    resetTotalMacrosAndCalories();
    deleteCheckmarkAndAddButton();
    createAddFoodButton();
    createCheckMarkButton();
}

const getMealWithNameFromDatabase = async (nameOfTheMeal) => {
    const response = await fetch(`http://localhost:8080/api/v1/meal/name=${nameOfTheMeal}`, {
        headers: {
            'Accept': 'application/json'
        }
    });
    return await response.json();
}

const getInputValuesFromFood = (foodInputs) => {
    let data = {};
    foodInputs.forEach(input => {
        data[input.name] = input.value;
        console.log(input.name);
        input.value = "";
    });
    return data;
}
//Send data to backend.
const saveMealToDatabase = (data) => {
    return fetch(`http://localhost:8080/api/v1/meal/addMeal`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)  
    });
} 
//Retrieve all foods from database.
const retrieveFoods = async () => {
    const response = await fetch('http://localhost:8080/api/v1/food/allFoods', {
        headers: {
            'Accept': 'application/json'
        }
    });
    return await response.json();
}
//Change visibility of table.
const changeTableVisibility = (boolean) => {
    const table = document.getElementById("tableFoods");
    boolean ? table.style.visibility = "visible" : table.style.visibility = "hidden";
}
const resetTotalMacrosAndCalories = () => {
    document.getElementById("protFoot").innerHTML = 0;
    document.getElementById("calFoot").innerHTML = 0;
    document.getElementById("fatsFoot").innerHTML = 0;
    document.getElementById("carbFoot").innerHTML = 0;
    document.getElementById("caloriesleftsofarNumber").innerHTML = 0;
    document.getElementById("caloriesleftNumber").innerHTML = 3000;
    totalCalories = 0;
    totalCarbs = 0;
    totalFats = 0;
    totalProtein = 0;
    caloriesLeft = 3000;
}

var totalRows = 0
var totalCalories = 0;
var caloriesLeft = 3000;
let totalFats = 0;
var totalProtein = 0;
var totalCarbs = 0;

//Generate rows into table
const generateRows = (foodsInDb) => {
    foodsInDb.then(m => {
        totalRows = m.length
        m.forEach(food => {
            const row = document.createElement("tr");
            const { name, calories, carbohydrates, protein, fats, weightInGrams } = food;
            const values = [name, weightInGrams, calories, carbohydrates, protein, fats];
            let cals = food["calories"];
            let rowName = food["name"];
            totalCalories += cals;
            caloriesLeft -= cals;
            values.forEach(value => {
                const cell = document.createElement("td");
                cell.textContent = value;
                row.appendChild(cell);
            });
                
            const table = document.getElementById("tableFoods");
            table.appendChild(row);
            totalFats += values[5];
            totalCarbs += values[3];
            totalProtein += values[4];
        });
        addToMacros(totalCarbs, totalFats, totalProtein, totalCalories, caloriesLeft);
    });
}
const generateSingleRow = (foodToAdd) => {
    const row = document.createElement("tr");
    let rowName = "";    
    for (i = 0; i < 6; i++) {
        const cell = document.createElement("td");
        var value = foodToAdd[i][1];
        if (!isNaN(value)) {
            value = parseInt(value);
        }
        const keyToVariableMap = {
            fats: (value) => { totalFats += value; },
            protein: (value) => { totalProtein += value; },
            carbohydrates: (value) => { totalCarbs += value; },
            calories: (value) => { totalCalories += value; },
        };
        if (keyToVariableMap.hasOwnProperty(foodToAdd[i][0])) {
            keyToVariableMap[foodToAdd[i][0]](value);
            console.log(keyToVariableMap[foodToAdd[i][0]]);
            cell.textContent = value;
        } else {
            cell.textContent = value;
            if (i == 0) {
                rowName = value;
            }
        }
        if (i == 5) {
            const div = document.createElement("div");
            const rowDeleteButton = createDeleteButton(rowName);
            rowDeleteButton.style.right = -40 + "px";
            div.style.position = "relative";
            div.appendChild(rowDeleteButton);
            cell.appendChild(div);
        }
        row.appendChild(cell);
    }
    const table = document.getElementById("tableFoods"); 
    table.appendChild(row);
    addToMacros(totalCarbs, totalFats, totalProtein, totalCalories, caloriesLeft);
}

const addToMacros = (totalCarbs, totalFats, totalProtein, totalCalories, caloriesLeft) => {
    document.getElementById("caloriesleftsofarNumber").innerHTML = Math.floor(totalCalories * 100) / 100;
    document.getElementById("caloriesleftNumber").innerHTML = caloriesLeft;
    document.getElementById("calFoot").innerHTML = Math.floor(totalCalories * 100) / 100;
    document.getElementById("protFoot").innerHTML = Math.floor(totalProtein * 100) / 100;
    document.getElementById("fatsFoot").innerHTML = Math.floor(totalFats * 100) / 100;
    document.getElementById("carbFoot").innerHTML = Math.floor(totalCarbs * 100) / 100;
}

const getExistingNextMealNamesFromDatabase = async (nextMealTime) => {
    const response = await fetch(`http://localhost:8080/api/v1/meal/mealtime=${nextMealTime}`, {
        headers: {
            'Accept': 'application/json'
        }
    });
    return await response.json();
}

const displayAllDeleteButtons = () => {
    const table = document.getElementById("tableFoods"); 
    for (i = 0; i < totalRows; i++) {
        row = table.getElementsByTagName("tr")[i];
        const deleteDiv = displayDeleteButtonNextToRow(row);
        table.rows[i].cells[5].appendChild(deleteDiv);
    }
}
const displayDeleteButtonNextToRow = (row) => {
    const table = document.getElementById("tableFoods");
    const div = document.createElement("div");
    let foodName = row.getElementsByTagName("td")[0].innerHTML;
    const rowDeleteButton = createDeleteButton(foodName);
    rowDeleteButton.style.right = -40 + "px";
    div.style.position = "relative";
    div.appendChild(rowDeleteButton);
    return div;
}
const createAnchorElementForMealDropdown = (mealNamesToDisplayList) => {
    mealNamesToDisplayList.then(n => {
        n.forEach(name => {
            const meal = document.createElement("a");
            const dropdownDiv = document.getElementById("mealDropdown");
            meal.innerHTML = name[1];
            meal.href = "#";
            meal.onclick = () => clearTableAndDisplayMealFoods(name[1]);
            dropdownDiv.appendChild(meal);
        })
    })
}

const createDeleteButton = (nameOfTheFoodInRow) => {
    const deleteButton = document.createElement("button");
    const deleteButtonImage = document.createElement("img");
    deleteButtonImage.src = "transparentLetterX.png";
    deleteButton.type = "submit";
    deleteButton.id = "deleteButton"
    deleteButton.value = nameOfTheFoodInRow;
    deleteButton.onclick = () => deleteVisualRowFromTable(deleteButton.value);
    deleteButton.appendChild(deleteButtonImage);
    return deleteButton;
}

const createCheckMarkButton = () => {
    const buttons = document.getElementById("but");
    const checkMark = document.createElement("button");
    const checkMarkImage = document.createElement("img");
    checkMarkImage.src = "greenCheckMark.png";
    checkMark.type = "submit";
    checkMark.id = "checkmark";
    checkMark.className= "editButton";
    checkMark.onclick = () => saveOrUpdateMealWhetherEditClicked();
    checkMark.appendChild(checkMarkImage);
    buttons.appendChild(checkMark);
    return checkMark;
}

const deleteVisualRowFromTable = (nameOfTheButtonRowFood) => {
    const table = document.getElementById("tableFoods");
    const foodRowsInTheTable = table.getElementsByTagName("tr"); 
    for (i = 0; i < foodRowsInTheTable.length; i++ ) {
        const rowElement = foodRowsInTheTable[i];
        const nameOfTheFoodInTable = rowElement.childNodes[0].innerHTML;
        if (nameOfTheFoodInTable.toUpperCase() === nameOfTheButtonRowFood.toUpperCase()) {
            rowElement.remove();
            removeMacroElementValuesFromTotal(rowElement);
            break;
        }
    }
}

const removeMacroElementValuesFromTotal = (rowElement) => {
    const foodMacros = rowElement.childNodes;
    console.log(foodMacros[5]);
    totalCalories -= parseInt(foodMacros[2].innerHTML);
    totalCarbs -= parseInt(foodMacros[3].innerHTML);
    totalProtein -= parseInt(foodMacros[4].innerHTML);
    totalFats -= parseInt(foodMacros[5].innerHTML);
    
    addToMacros(totalCarbs, totalFats, totalProtein, totalCalories, 3000 - totalCalories);
    console.log(foodMacros);
}

const editMeal = () => {
    if (editPencilClicked) {
        return;
    }
    displayAllDeleteButtons();
    editPencilClicked = true;
    changeEditButtonColor(editPencilClicked);
    deleteCheckmarkAndAddButton();
    createAddFoodButton();
    createCheckMarkButton();
}

const createAddFoodButton = () => {
    const buttons = document.getElementById("but");
    const addFoodButton = document.createElement("button");
    addFoodButton.type = "submit";
    addFoodButton.id = "addFoodButton";
    addFoodButton.className = "editButton";
    addFoodButton.innerHTML = "Add Food";
    addFoodButton.onclick = () => openAddFoodPopup();
    buttons.appendChild(addFoodButton);
    return addFoodButton;
}

const deleteCheckmarkAndAddButton = () => {
    const buttons = document.getElementById("but");
    while(buttons.firstChild) {
        buttons.removeChild(buttons.lastChild);
    }
}

const removeOnlyDeleteButtons = () => {
    const deleteButtons = document.querySelectorAll("#deleteButton");
    deleteButtons.forEach(button => {
        button.remove();
    })
}

const checkInputsCorrectValues = () => {
    const inputs = document.querySelectorAll('.input');
    const inputsSize = inputs.length;
    for (i = 0; i < inputsSize; i++) {
        let input = inputs[i];
        if (input.value == null 
            || input.value == "" 
            || (input.type == "text" && !isNaN(+input.value))) {
            return false;
        }
    }
    return true;
};
