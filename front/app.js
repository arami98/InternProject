const express = require('express');
const app = express();
app.listen(3000,function() {
    console.log("Express server start on port 3000");
});


// localhost:3000/main 브라우저에 res.sendFile() 내부의 파일이 띄워진다.
app.get('/', function(req,res) {
    res.sendFile(__dirname + "/index.html");
});

app.use(express.static(''))