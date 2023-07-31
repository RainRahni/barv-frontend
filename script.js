//const { response } = require("express");

//const { send } = require("express/lib/response");
window.addEventListener('load', () => {
    const data = retrieveFoods()
    .then((resolvedValue) => {
        console.log(Object.entries(resolvedValue));
        return Object.entries(resolvedValue);
    });
    console.log(data.then(m => {
        console.log(m[0][1])}))
    data.length != 0 ? changeTableVisibility(true) : changeTableVisibility(false);
    generateRows(data);
});

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
        return "Breakfast";
    }else if (displayTimeOfMeal() == "13:00"){
        return "Lunch";
    }else{
        return "Dinner";
    }
};
document.getElementById("nextmealdisplay").innerHTML = displayNextMeal()
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
    /**sendDataToBackEnd(data, "food/addFood").then(() => {
        const dataTwo = retrieveFoods().then((data) => {
            const entries = Object.entries(data);
            return entries[entries.length - 1];
        })
        generateSingleRow(dataTwo);
    });*/
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
    console.log(meal);
    sendDataToBackEnd(meal, "meal/addMeal");
}
//Add and remove meal popup from screen
let mealPopup = document.getElementById("mealpopup");
function openMealPopup() {
    mealPopup.classList.add("open-popup"); 
}
let mealTime = "";
//Get meal time from user and assign it.
function chosenMeal(meal) {
    mealPopup.classList.remove("open-popup");
    mealTime = meal;
    const checkMark = document.getElementById("checkmark");
    checkMark.style.visibility="visible";
    const table = document.getElementById("tableFoods");
    table.innerHTML = "";
    resetTotalMacros();
}

//Assign each value from text box to its key and put it into object.
const getInputValues = (inputs, data) => {
    inputs.forEach(input => {
        data[input.name] = input.value;
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
const resetTotalMacros = () => {
    document.getElementById("protFoot").innerHTML = 0;
    document.getElementById("calFoot").innerHTML = 0;
    document.getElementById("fatsFoot").innerHTML = 0;
    document.getElementById("carbFoot").innerHTML = 0;
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
            const { name, calories, carbohydrates, protein, fats, weight } = food[1];
            const values = [name, weight, calories, carbohydrates, protein, fats];
            let cals = food[1]["calories"];
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
/**Generate 1 row to table.
const generateSingleRow = (foodToAdd) => {
    foodToAdd.then(m => {
            const row = document.createElement("tr");
            const { name, calories, carbohydrates, protein, fats, weight } = foodToAdd[1];
            const values = [name, weight, calories, carbohydrates, protein, fats];
            let cals = foodToAdd[1]["calories"];
            totalCalories += cals;
            caloriesLeft -= cals;
            values.forEach(value => {
                const cell = document.createElement("td");
                cell.textContent = value;
                row.appendChild(cell);
            });
            totalFats += values[5];
            totalCarbs += values[3];
            totalProtein += values[4];
            const table = document.getElementById("tableFoods");
            const btn = document.getElementById("btny");
            table.appendChild(row);
            addToMacros(totalCarbs, totalFats, totalProtein, totalCalories, caloriesLeft);
    });
}*/
const generateSingleRow = (foodToAdd) => {
    const row = document.createElement("tr");                 
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
            calories: (value) => { totalCalories += value; }
        };
        if (keyToVariableMap.hasOwnProperty(foodToAdd[i][0])) {
            keyToVariableMap[foodToAdd[i][0]](value);
            cell.textContent = value;
        } else {
            cell.textContent = value;
        }
        row.appendChild(cell);
        }
            /**const row = document.createElement("tr");
            const { name, calories, carbohydrates, protein, fats, weight } = foodToAdd[0];
            const values = [name, weight, calories, carbohydrates, protein, fats];
            let cals = foodToAdd[5]["calories"];
            totalCalories += cals;
            caloriesLeft -= cals;
            values.forEach(value => {
                const cell = document.createElement("td");
                cell.textContent = value;
                row.appendChild(cell);
            });*/
            //totalFats += values[5];
            //totalCarbs += values[3];
            //totalProtein += values[4];
            const table = document.getElementById("tableFoods");
            table.appendChild(row);
            addToMacros(totalCarbs, totalFats, totalProtein, totalCalories, caloriesLeft);
    //});
}
//Add values to footer in the table.
const addToMacros = (totalCarbs, totalFats, totalProtein, totalCalories, caloriesLeft) => {
    document.getElementById("caloriessofar").innerHTML += totalCalories;
    document.getElementById("caloriesleft").innerHTML += caloriesLeft;
    document.getElementById("calFoot").innerHTML = totalCalories;
    document.getElementById("protFoot").innerHTML = totalProtein;
    document.getElementById("fatsFoot").innerHTML = totalFats;
    document.getElementById("carbFoot").innerHTML = totalCarbs;
}


