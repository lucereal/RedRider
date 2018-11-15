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
            resolve({
                querysuccess: false,
                queryresults: result
            })
        })
    });




}

exports.signup = function (email, password, callback) {
    //if user already exists
    this.login(email, password, function (result) {
        if (result.querysuccess == true) {
            callback({
                querysuccess: false,
                msg: "user exisits"
            })
        }
        else {
            connection.query('insert into profile(Email, Password) VALUES (?, ?)', [email, password], function (error, results) {
                if (error) {//if insert error
                    console.log("errors: " + error);
                    callback({
                        querysuccess: false,
                        queryresults: "insert failed"
                    })
                } else {

                    // if (results.length < 1) {//if results is empty
                    //     callback({
                    //         querysuccess: false,
                    //         queryresults: results
                    //     })
                    // } else {


                    callback({
                        querysuccess: true,
                        queryresults: results
                    });
                    //}
                }



            })

        }
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
        } //else {
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

exports.getposts = function (userId, callback) {
    connection.query("select * from riderpost where RiderID=?", [userId], function (error, resultrider) {
        connection.query("select * from driverpost where DriverID=?", [userId], function (error, resultdriver) {
            if (error) {
                console.log("errors: " + error);
                callback({
                    querysuccess: false,
                    queryresults: "insert failed"
                })
            }
            {
                callback({
                    querysuccess: true,

                    queryresultdriver: resultdriver,
                    queryresultrider: resultrider

                });
            }


        })
    })


}