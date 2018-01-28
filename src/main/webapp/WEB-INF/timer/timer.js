

var timers = (function () {

    var ready = false, timers = {}, nextId = 0, interval, tbody, idLot;

    function newElement (tagName) {
        return document.createElement(tagName);
    }

    function byId (id) {
        return document.getElementById(id);
    }

    function parseInputInt (id) {
        var value = byId(id).value;
        return value != ""? parseInt(value, 10): 0;
    }

    function now () {
        return (new Date()).getTime();
    }

    function init () {

        interval = setInterval(update, 100);
        tbody = byId("timers-table").tBodies[0];
        ready = true;
    }

    function update () {

        var id, timer, timeNow = now(), timeLeft, h, m, s;

        for (id in timers) { if (timers.hasOwnProperty(id)) {

            timer = timers[id];

            if (timer.state == "active") {

                timeLeft = timer.deadline - timeNow;

                if (timeLeft > 0) {
                    d = Math.floor(timeLeft / 86400000)
                    h = Math.floor(timeLeft % 86400000 / 3600000);
                    m = Math.floor(timeLeft % 3600000 / 60000);
                    s = Math.floor(timeLeft % 60000 / 1000);

                    timer.row.cells[1].innerHTML =
                        (d < 10? "0" + d: d) + ":" +
                        (h < 10? "0" + h: h) + ":" +
                        (m < 10? "0" + m: m) + ":" +
                        (s < 10? "0" + s: s);
                }

                else {

                    timer.state = "fired";
                    timer.row.className = "timer-fired";
                    timer.row.cells[1].innerHTML = "00:00:00";
                }
            }
        }}
    }

    function add (name, duration) {

        if (!ready) { init(); }

        var row, cell, control, button;

        row = tbody.insertRow(-1);
        row.className = "timer-active";
        row.insertCell(-1).innerHTML = name;
        row.insertCell(-1).innerHTML = "&ndash;&ndash;:&ndash;&ndash;:&ndash;&ndash;";


        cell = row.insertCell(-1);
        button = cell.appendChild(newElement("span"));
        button.className = "timer-button";
        button.innerHTML = "тут будет текущая ставка и кто поставил";



      //  cell = row.insertCell(-1);

        // control = cell.appendChild(newElement("span"));
        // control.className = "timer-control";
        // control.innerHTML = "пауза";
        // control.onclick = (function (id) { return function () { suspend(id); }})(nextId);
        //
        // control = cell.appendChild(newElement("span"));
        // control.className = "timer-control";
        // control.innerHTML = "удалить";
        // control.onclick = (function (id) { return function () { remove(id); }})(nextId);

        cell = row.insertCell(-1);
        button = cell.appendChild(newElement("span"));
        button.className = "timer-button";
      //  document.write(idLot);
        button.innerHTML = "<p style=\"text-align: center\"><a href=lotPage?idLot="
        +idLot+
        "> <button>Сделать ставку</button></a></p>";
//        control.onclick = (function (id) { return function () { suspend(id); }})(nextId);


       // cell = row.insertCell(-1);

        timers[nextId++] = {
            state: "active",
            deadline: now() + duration,
            row: row
        };
    }

   function launch (p) {
      idLot = p;
        var tag =  "time"+p+"";

        var name = document.getElementById( tag).getAttribute("name");
        var finish = document.getElementById( tag).getAttribute("dataFinish");
        var start= document.getElementById(tag).getAttribute("dataStart");

        var duration =  finish - ((new Date()).getTime() - start);

        var d ;
        var h ;
        var m ;
        var s ;
        //              var duration = (new Date()).getTime()+ finish - ((new Date()).getTime() - start)

        //var duration = h * 3600000 + m * 60000 + s * 1000;

        if (isFinite(duration) && duration > 0) {
            add(name, duration);
        }
    }

    function suspend (id) {

        var timer = timers[id];

        switch (timer.state) {

            case "active":

                timer.row.className = "timer-suspended";
                timer.remain = timer.deadline - now();
                timer.state = "suspended";
                break;

            case "suspended":

                timer.row.className = "timer-active";
                timer.deadline = now() + timer.remain;
                timer.state = "active";
                break;
        }
    }

    function remove (id) {

        tbody.deleteRow(timers[id].row.rowIndex);
        delete timers[id];
    }

    function removeAll () {

        var id;

        for (id in timers) { if (timers.hasOwnProperty(id)) {

            tbody.deleteRow(timers[id].row.rowIndex);
            delete timers[id];
        }}
    }

    function removeFired () {

        var id;

        for (id in timers) { if (timers.hasOwnProperty(id)) {

            if (timers[id].state == "fired") {

                tbody.deleteRow(timers[id].row.rowIndex);
                delete timers[id];
            }
        }}
    }

    return {
        launch:  launch,
        removeAll: removeAll,
        removeFired: removeFired
    };

})();
