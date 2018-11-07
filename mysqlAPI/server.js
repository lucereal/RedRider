const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors'); //MW so we can make req to our api from different domain
const app = express();
const mysql = require('mysql');
const db = require('./dbmanager');
//Cors Middleware
// app.use(function (req, res, next) {
//     //Enabling CORS 
//     res.header("Access-Control-Allow-Origin", "*");
//     res.header("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
//     res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, contentType,Content-Type, Accept, Authorization");
//     next();
// });

app.use(cors());

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));



app.get('/', function (req, res) {
    app.send('invlalid endpoint');
});

function handleResult(result, res) {
    if (result.querysuccess) {
        res.json({
            success: true,
            body: result.queryresults[0]
        })
        success = true;
        body = result.queryresults[0];

    } else {
        res.json({
            success: false,
            body: result.queryresults
        })
        success = false;
        body = result.queryresults;

    }
}

app.post('/query', function (req, res, next) {

    const query = req.body.query;
    const params = req.body.params;
    let success;
    let body;

    console.log("query: " + query);
    console.log("params: " + JSON.stringify(params));
    switch (query) {
        case 'login':
            var email = params.email;
            var password = params.password;
            console.log("email: " + email);
            console.log("password: " + password);

            db.login(email, password, function (result) {
                //console.log(JSON.stringify(result));
                if (result.querysuccess) {
                    res.json({
                        success: true,
                        body: result.queryresults[0]
                    })
                    success = true;
                    body = result.queryresults[0];

                } else {
                    res.json({
                        success: false,
                        body: result.queryresults
                    })
                    success = false;
                    body = result.queryresults;

                }

            })
            break;
        case 'makepost':
            res.json({
                success: true,
                body: "makepost success"
            })
            break;
        default:
            console.log("invalid query type");

    }



})

app.post('/login', function (req, res) {
    const email = req.body.email;
    const password = req.body.password;

    console.log("email: " + email);
    console.log("password: " + password);

    db.login(email, password, function (result) {
        //console.log(JSON.stringify(result));

        if (result.querysuccess) {
            success = true;
            body = result.queryresults[0];
            res.json({
                success: true,
                user: result.queryresults[0]
            })

        } else {
            success = false;
            body = result.queryresults;
            res.json({
                success: false,
                user: result.queryresults
            })

        }



    })
    // var user = {
    //     email: email,
    //     password: password
    // }


})

app.listen(3000, function () {
    console.log('Listening on port 3000.');
})

