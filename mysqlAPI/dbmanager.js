const mysql = require('mysql');
const util = require('util');

const connection = mysql.createConnection({
    host: '75.111.163.91',
    port: 3306,
    user: 'general',
    password: 'password',
    insecureAuth: true,
    database: 'codemalone'
});

connection.connect(function (err) {
    if (err) {
        console.error('error connecting: ' + err.stack);
        return;
    }
    console.log('connected as id ' + connection.threadId);
});

connection.query = util.promisify(connection.query).bind(connection);

//put queries here
//connection.end();
exports.login = function (email, password, callback) {

    console.log("email and password: " + email + " " + password);
    return new Promise(function (resolve, reject) {

        connection.query('select * from profile where Email = ? and Password = ?', [email, password]).then(result => {
            console.log("result: " + JSON.stringify(result));
            if (result.length < 1) {
                resolve({
                    querysuccess: false,
                    queryresults: result
                });
            } else {
                resolve({
                    querysuccess: true,
                    queryresults: result
                })
            }
        }).catch(error => {
            console.log(error.stack);
            resolve({
                querysuccess: false,
            })
        })
    });




}

exports.signup = function (email, password, callback) {
    //if user already exists
    //console.log('here');
    this.login(email,password).then(result =>{
        if (result.querysuccess == true) {
            callback({
                querysuccess: false,
                msg: "user exisits"
            })
        }
        else {
            console.log(1);
            connection.query('insert into profile(Email, Password) VALUES (?, ?)', [email, password], function (error, results) {
                if (error) {//if insert error
                    console.log("errors: " + error);
                    callback({
                        querysuccess: false,
                        queryresults: "insert failed"
                    })
                } else {
                    console.log("results: " + results);
                    callback({
                        querysuccess: true,
                        queryresults: results
                    });
                }
            })
        }
    })
}

exports.matchrespond = function (userID,tripID,response,callback){
    connection.query("update matching set riderresponse=? where RiderID=? and tripID=?",[response,userID,tripID],function(error,results){
        if(error){
            callback({
                querysuccess:false
            })
        }
    connection.query("update matching set driverresponse=? where DriverID=? and tripID=?",[response,userID,tripID], function(error1,results1){
        if(error1){
            callback({
                querysuccess:false
            })
        }
        callback({
            querysuccess:true,
            
        })
    })
    })
   
}

exports.updateProfile = function (profileId, name, major, age, sex, callback) {

    connection.query("update profile set Name=?, Major=?,Age=?,Sex=? where idProfile=?", [name, major, age, sex, profileId], function (error, results) {
        if (error) {//if insert error
            console.log("errors: " + error);
            callback({
                querysuccess: false,
                queryresults: "insert failed"
            })
        } else {
            callback({
                querysuccess: true,
                queryresults: results
            })
        }
    })
}

exports.driverpost = function (driverid, vehicle, destination, time, date, seats, callback) {
    if (time == null)
        time = 0;

    connection.query("insert into driverpost(DriverID, Vehicle, DestinationID, Time, Date, Seats) values(?,?,?,?,?,?)", [driverid, vehicle, destination, time, date, seats], function (error, results) {
        if (error) {
            console.log("errors: " + error);
            callback({
                querysuccess: false,
                queryresults: "insert failed"
            })
        }
        //else {
        //console.log("results: " + JSON.stringify(results));
        if (results.length < 1) {
            callback({
                querysuccess: false,
                queryresults: results
            })
        } else {
            callback({
                querysuccess: true,
                queryresults: results
            });
        }

    })
}

exports.riderpost = function (riderid, date, destination, time, callback) {
    if (time == null)
        time = 0;

    connection.query("insert into riderpost(RiderID, DestinationID, Time, Date) values(?,?,?,?)", [riderid, destination, time, date], function (error, results) {
        if (error) {
            console.log("errors: " + error);
            callback({
                querysuccess: false,
                queryresults: "insert failed"
            })
        }
        if (results.length < 1) {
            callback({
                querysuccess: false,
                queryresults: results
            })
        } else {
            callback({
                querysuccess: true,
                queryresults: results
            });
        }
    })
}

exports.getposts = function (userID, callback) {
    connection.query("select * from riderpost where RiderID=?", [userID], function (error, resultrider) {
        connection.query("select * from driverpost where DriverID=?", [userID], function (error, resultdriver) {
            if (error) {
                console.log("errors: " + error);
                callback({
                    querysuccess: false,
                    queryresults: "insert failed"
                })
            }
            {
                console.log("\nRider posts: " + JSON.stringify(resultrider));
                console.log("\nDriver posts: " + JSON.stringify(resultdriver));
                callback({
                    querysuccess: true,
                    queryresultdriver: resultdriver,
                    queryresultrider: resultrider

                });
            }
        })
    })
}

exports.getmatches = function (userId, callback) {
    let obj = {
        matchpost:{},
        riderprofile:{},
        driverprofile:{}
    }
    
    let matchArray = [];
    let index = 0;
    connection.query("select * from matching where DriverID=? or RiderID=?", [userId, userId], function (error, result) {
        if (error) {
            console.log("Errors: " + error)
            callback({
                querysuccess: false,
                queryresults: "get match posts failed"
            })
        }
        
        let riderid;
        let driverid;

      
        if(result.length < 1){
            callback({
                querysuccess:false,
                queryresults:[]
            })
        }
        for(let i = 0; i<result.length; i++){
           
            riderid = result[i].RiderID;
            driverid = result[i].DriverID;
            console.log(result[i].RiderID);
        connection.query("select * from profile where idProfile = ?", [riderid], function (error1, result1) {
            if (error) {
                console.log("Errors: " + error1)
                callback({
                    querysuccess: false,
                    queryresults: "get rider from profile failed"
                })
            }
           
            
            connection.query("select * from profile where idProfile=?", [driverid], function (error2, result2) {
                if (error) {
                    console.log("Errors: " + error2)
                    callback({
                        querysuccess: false,
                        queryresults: "get driver from profile failed"
                    })
                }
                
                console.log("result1: " + JSON.stringify(result1));
                console.log("result2: " + JSON.stringify(result2));
                 if(result1 != undefined && result2 != undefined && result1.length > 0 && result2.length >0){
                    matchArray.push({
                        matchpost:result[i],
                        riderprofile:result1[0],
                        driverprofile:result2[0],
                    })
                }
               

                
                if(index >= result.length-1){
                    
                    if(matchArray.length > 0){
                        callback({
                            querysuccess: true,
                            matchArray: matchArray
                        })
                    }else{
                        callback({
                            querysuccess: false,
                            matchArray: "no match"
                        })
                    }
                    
                   
                }
                index++;
                

              
            })//end query
        })//eng query
    }//end for
    })
}