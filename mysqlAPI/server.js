const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors'); //MW so we can make req to our api from different domain
const app = express();
const mysql = require('mysql');
const db = require('./dbmanager');


app.use(cors());

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));



app.get('/', function (req, res) {
    app.send('invlalid endpoint');
});

app.post('/login', function (req, res) {
    const email = req.body.email;
    const password = req.body.password;

    console.log("email: " + email);
    console.log("password: " + password);

    db.login(email,password).then(result=>{
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
    // db.login(email, password, function (result) {
    //     //console.log(JSON.stringify(result));

        



    // })
  


})

app.post('/signup', function (req, res) {
    const email = req.body.email;
    const password = req.body.password;
    console.log("email: " + email);
    console.log("password: " +password);
    // const major = req.body.major;
    // const name = req.body.name;
    // const age = req.body.age;
    // const sex = req.body.sex;

  
    db.signup(email, password, function (result) {
        console.log(JSON.stringify(result));
        
        if (result.querysuccess) {
            success = true;
            body = result.queryresults;

            console.log("signup success");
            res.json({
                success: true,
                insertresults: result.queryresults,
                profileId: result.queryresults.insertId
            })

        } else {
            success = false;
            body = result.queryresults;
            if (result.msg) {
                res.json({
                    success: false,
                    msg: result.msg
                })
            } else {
                res.json({
                    success: false,
                    insertresults: result.queryresults

                })
            }


        }


    })
})

app.post('/updateprofile', function(req,res){
    const profileId = req.body.profileId;
    const name = req.body.name;
    const sex = req.body.sex;
    const major = req.body.major;
    const age = req.body.age;

    db.updateProfile(profileId,name, major, age, sex, function(result){

        console.log(JSON.stringify(result));
        if(result.querysuccess){
            res.json({
                success:true,
                results:result
            })
        }else{
            res.json({
                success:false,
                results:result
            })
        }

    })

    

})

app.post('/driverpost', function (req, res) {

    const driverid = req.body.driverid;
    const vehicle = req.body.vehicle;
    const seats = req.body.seats;
    const destination = req.body.destination;
    const time = req.body.time;
    const date = req.body.date;

    db.driverpost(driverid, vehicle, destination, time, date, seats, function (result) {
        if (result.querysuccess) {
            success = true;
            body = result.queryresults;
            res.json({
                success: true,
                postId: body.insertId,
                insertresults: result.queryresults,
            })
        } else {
            success = false;
            body = result.queryresults;
        }
    })
})

app.post('/riderpost', function (req, res) {

    const riderid = req.body.riderid;
    const destination = req.body.destination;
    const time = req.body.time;
    const date = req.body.date;
    db.riderpost(riderid, date, destination, time, function (result) {
        if (result.querysuccess) {
            success = true;
            body = result.queryresults;
            res.json({
                success: true,
                postId: body.insertId,
                insertresults: result.queryresults,
            })
        } else {
            success = false;
            body = result.queryresults;
        }
    })
})

app.post('/getmatches',function (req,res){
    const userid = req.body.userId

    db.getmatches(userid,function(result){
        if (result.querysuccess) {
            success = true
            body = result.queryresults
            res.json({
                success: true,
                matchArray: result.matchArray,
            })
        }else{
            success = false,
            body = results.queryresults
        }

    //    riderprofile: result1,
    //    driverprofile: result2,
    //    matchpost: result
    })
})

app.post('/getposts', function (req, res) {

    const userid = req.body.userId

    db.getposts(userid, function (result) {
        if (result.querysuccess) {
            success = true;
            body = result.queryresults;
            // console.log(result);
            res.json({
                success: true,
                //postId:body.insertId,
                riderposts: result.queryresultrider,
                driverposts: result.queryresultdriver
            })
        } else {
            success = false;
            body = result.queryresults;
        }
    })

})



app.listen(3001, function () {
    console.log('Listening on port 3000.');
})

