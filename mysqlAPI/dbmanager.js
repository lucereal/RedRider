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
exports.login = function (email, password) {

    connection.query('select * from profile where Email = ? and Password = ?', [email, password], function (error, results, fields) {
        if (error) {
            console.log("errors: " + error);
        }
        console.log("results: " + JSON.stringify(results));
    })

}


