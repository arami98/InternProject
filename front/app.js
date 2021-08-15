const express = require('express');
const app = express();
app.listen(3000,function() {
    console.log("Express server start on port 3000");
});


app.get('/', function(req,res) {
    res.sendFile(__dirname + "/public/index.html");
});

app.use(express.static('public'));