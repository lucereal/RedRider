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
                querysuccess:false,
                queryresults: results
            })
        }
        
         console.log("results: " + JSON.stringify(results));
         if(results.length < 1){
             callback({
                 querysuccess:false,
                 queryresults: results
             })
         }else{
            callback({
                querysuccess: true,
                queryresults: results
            });
         }
         
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
    })

}


