
window.addEventListener('load', () => {
    const datas = getExistingNextMealNamesFromDatabase(displayNextMeal())
    .then((resolvedValue) => {
        return Object.entries(resolvedValue);
    });
    createAnchorElementForMealDropdown(datas);
});
const clearTableAndDisplayMealFoods = (specificMealName) => {
    eraseAllRowsFromScreen();
    const data = getMealWithNameFromDatabase(specificMealName)
    .then((resolvedValue) => {
        return Object.entries(resolvedValue)[7][1];
    });
    resetTotalMacrosAndCalories();
    generateRows(data);
    console.log("generate multiple");
    console.log(data);
    
}
const eraseAllRowsFromScreen = () => {
    const table = document.getElementById("tableFoods");
    table.innerHTML = "";
}
// Display what weekday it currently is
const currentDay = () => {
    const weekdays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
    const d = new Date();
    return weekdays[d.getDay()].toUpperCase();
};
document.getElementById("weekday").innerHTML = currentDay();
// Display the time of the next meal
// 13.00 lõuna, õhtu 18:00-22.00, hommik 7:45-9.00
const displayTimeOfMeal = () =>{
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
//Display next meal.
const displayNextMeal = () => {
    if (displayTimeOfMeal() == "8:20"){
        return "BREAKFAST";
    }else if (displayTimeOfMeal() == "13:00"){
        return "LUNCH";
    }else{
        return "DINNER";
    }   
};
document.getElementById("nextmealdisplay").innerHTML = displayNextMeal();
//Open popup
let popup = document.getElementById("popup");
function openPopup() {
    popup.classList.add("open-popup");
    
}
const mealData = [];
//Remove food popup from the screen.
function closePopup() {
    popup.classList.remove("open-popup");
    const inputs = document.querySelectorAll(".input");
    let data = {};
    getInputValues(inputs, data);
    const entries = Object.entries(data);
    generateSingleRow(entries);
    mealData.push(data);
    changeTableVisibility(true);
}

//Construct meal class and send it to database
const constructMealAndSend = () => {
    let meal = {};
    let nameOfMeal = mealTime + "-" + totalCalories;
    Object.assign(meal, {name : nameOfMeal});
    Object.assign(meal, {calories : totalCalories});
    Object.assign(meal, {protein : totalProtein});
    Object.assign(meal, {carbohydrates : totalCarbs});
    Object.assign(meal, {fats : totalFats});
    Object.assign(meal, {type : mealTime.toUpperCase()});
    Object.assign(meal, {foods : mealData});
    sendDataToBackEnd(meal, "meal/addMeal");
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
//Assign each value from text box to its key and put it into object.
const getInputValues = (inputs, data) => {
    inputs.forEach(input => {
        data[input.name] = input.value;
        console.log(input.name);
        input.value = "";
    });
}
//Send data to backend.
const sendDataToBackEnd = (data, url) => {
    return fetch(`http://localhost:8080/api/v1/${url}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
} 
//Retrieve all foods from database.
const retrieveFoods = async () => {
    const response = await fetch('https://rainrahni.github.io/barv//api/v1/food/allFoods', {
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
var totalCalories = 0;
var caloriesLeft = 3000;
let totalFats = 0;
var totalProtein = 0;
var totalCarbs = 0;

//Generate rows into table
const generateRows = (foodsInDb) => {
    foodsInDb.then(m => {
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
            const cell = document.createElement("td");
            const div = document.createElement("div");
            const rowDeleteButton = createDeleteButton(rowName);
            rowDeleteButton.style.right = -40 + "px";
            div.style.position = "relative";
            div.appendChild(rowDeleteButton);
            cell.appendChild(div);  
            row.appendChild(cell);     
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
    console.log(foodToAdd);
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
//Add values to footer in the table.
const addToMacros = (totalCarbs, totalFats, totalProtein, totalCalories, caloriesLeft) => {
    document.getElementById("caloriesleftsofarNumber").innerHTML = totalCalories;
    document.getElementById("caloriesleftNumber").innerHTML = caloriesLeft;
    document.getElementById("calFoot").innerHTML = totalCalories;
    document.getElementById("protFoot").innerHTML = totalProtein;
    document.getElementById("fatsFoot").innerHTML = totalFats;
    document.getElementById("carbFoot").innerHTML = totalCarbs;
}

const getExistingNextMealNamesFromDatabase = async (nextMealTime) => {
    const response = await fetch(`https://rainrahni.github.io/barv/api/v1/meal/mealtime=${nextMealTime}`, {
        headers: {
            'Accept': 'application/json'
        }
    });
    return await response.json();
}

const createAnchorElementForMealDropdown = (mealNamesToDisplayList) => {
    mealNamesToDisplayList.then(n => {
        n.forEach(name => {
            const meal = document.createElement("a");
            const dropdownDiv = document.getElementById("mealDropdown");
            meal.innerHTML = name[1];
            meal.href = "#";
            meal.onclick= () => clearTableAndDisplayMealFoods(name[1]);
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
    checkMark.onclick = () => constructMealAndSend();
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
        console.log(rowElement);
        console.log(nameOfTheButtonRowFood);
        console.log(nameOfTheFoodInTable);
        if (nameOfTheFoodInTable.toUpperCase() === nameOfTheButtonRowFood.toUpperCase()) {
            rowElement.remove();
            removeMacroElementValuesFromTotal(rowElement);
            break;
        }
    }
}
const removeMacroElementValuesFromTotal = (rowElement) => {
    const foodMacros = rowElement.childNodes;
    totalCarbs -= parseInt(foodMacros[2].innerHTML);
    totalProtein -= parseInt(foodMacros[3].innerHTML);
    totalFats -= parseInt(foodMacros[4].innerHTML);
    totalCalories -= parseInt(foodMacros[5].innerHTML);
    
    addToMacros(totalCarbs, totalFats, totalProtein, totalCalories, 3000 - totalCalories);
    console.log(foodMacros);
}

const editMeal = () => {
    const editButton = document.getElementById("editPencil");
    editButton.style.backgroundColor = "white";
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
    addFoodButton.onclick = () => openPopup();
    buttons.appendChild(addFoodButton);
    return addFoodButton;
}

const deleteCheckmarkAndAddButton = () => {
    const buttons = document.getElementById("but");
    while(buttons.firstChild) {
        buttons.removeChild(buttons.lastChild);
    }
}

