/*
 *Author: Guanhua Fan
 *failure is the mother of success
 * don't give up if you can
 * */

var car = {
    name: 4,
    getName: function () {
        console.log(this.name);
    }
}

var getNameHere = car.getName;

getNameHere(); // what do you get? undefined, right
//why & how to fix it

var getNameHere = car.getName.bind(car);

// this keyword
this == Window in the browser window
how about in node?
    this ==  module.exports;
