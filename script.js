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
$(document).on('click', '#btnoke', function () {
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
//Remove popup from the screen.
function closePopup() {
    popup.classList.remove("open-popup");
    const inputs = document.querySelectorAll(".input");
    const data = {};
    getInputValues(inputs, data);
    sendDataToBackEnd(data);
    //addFoodInformationRow(data);
    changeTableVisibility(true);

}
//Assign each value from text box to its key and put it into object.
const getInputValues = (inputs, data) => {
    inputs.forEach(input => {
        data[input.name] = input.value;
        input.value = "";
    });
}
//Send data to backend.
const sendDataToBackEnd = (data) => {
    fetch('http://localhost:8080/api/v1/food/addFood', {
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

//Add new row of food.
const addFoodInformationRow = (data) => {
    const row = document.createElement("div");
    Object.values(data).forEach(value => {
        const cell = document.createElement("div");
        cell.textContent = value;
        row.appendChild(cell);
    });
    const table = document.getElementById('foods');
    table.appendChild(row);
}
//Change visibility of table.
const changeTableVisibility = (boolean) => {
    const table = document.getElementById("tableFoods");
    boolean ? table.style.visibility = "visible" : table.style.visibility = "hidden";
}
var totalCalories = 0;
var caloriesLeft = 3000;

//Generate rows into table
const generateRows = (foodsInDb) => {
    foodsInDb.then(m => {
        m.forEach(food => {
            const row = document.createElement("tr");
            const { name, calories, carbohydrates, protein, fats, weight } = food[1];
            const values = [name, weight, calories, carbohydrates, protein, fats];
            values.forEach(value => {
                if (value == calories) { 
                    totalCalories += value;
                    caloriesLeft -= value;
                }
                const cell = document.createElement("td");
                cell.textContent = value;
                row.appendChild(cell);
            });
            const table = document.getElementById("tableFoods");
            const btn = document.getElementById("btny");
            table.appendChild(row);
        });
        document.getElementById("caloriessofar").innerHTML += " " + totalCalories;
        document.getElementById("caloriesleft").innerHTML += " " + caloriesLeft;
    });
}



