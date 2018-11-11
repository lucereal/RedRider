const mysql = require('mysql');

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

//put queries here
//connection.end();
exports.login = function (email, password, callback) {

    console.log("email and password: " + email + " " + password);
    connection.query('select * from profile where Email = ? and Password = ?', [email, password], function (error, results, fields) {
        if (error) {
            console.log("errors: " + error);
            callback({
                querysuccess: false,
                queryresults: results
            })
        }

        console.log("results: " + JSON.stringify(results));
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
        {
            // if(results.length < 1){
            //     return {
            //         success: false,
            //         results: {}
            //     }
            // }

            // return {
            //     success:true,
            //     results: results[0]
            // };
        }
    })

}

exports.signup = function (name, email, password, major, age, sex, callback) {
    this.login(email, password, function (result) {
        if (result.querysuccess == true) {
            callback({
                querysuccess: false,
                msg: "user exisits"
            })
        }
        else {
            connection.query('insert into profile(Name, Major, Age, Sex, Email, Password) VALUES (?, ?, ?, ?, ?, ?)', [name, major, age, sex, email, password], function (error, results) {
                if (error) {
                    console.log("errors: " + error);
                    callback({
                        querysuccess: false,
                        queryresults: "insert failed"
                    })
                } //else {
                    console.log("results: " + JSON.stringify(results));
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
                //}



            })

        }
    })



}



exports.driverpost = function(driverid,vehicle,destination,time,date,seats,callback){
    if(time==null)
        time=0;

      connection.query("insert into driverpost(DriverID, Vehicle, DestinationID, Time, Date, Seats) values(?,?,?,?,?,?)",[driverid,vehicle,destination,time,date,seats],function(error,results) {
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

exports.riderpost = function(riderid,date,destination,time,callback){
    if(time==null)
        time=0;

      connection.query("insert into riderpost(RiderID, DestinationID, Time, Date) values(?,?,?,?)",[riderid,destination,time,date],function(error,results) {
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

exports.getposts=function(userId,callback){
    connection.query("select * from riderpost where RiderID=?",[userId],function(error,resultrider){
        connection.query("select * from driverpost where DriverID=?",[userId],function(error,resultdriver){
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