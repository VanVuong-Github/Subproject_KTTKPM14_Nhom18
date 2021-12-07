const express = require("express");

const app = express();

app.use(express.json({ extended: false }));
app.use(express.static("./"));
app.set("view engine", "ejs");
app.set("views", "./ejs");

app.get("/", (req, res) => {
  return res.render("trangchu");
});
app.get("/dangnhap/", (req, res) => {
  return res.render("dangnhap");
});
app.get("/dangky/", (req, res) => {
  return res.render("dangky");
});
app.listen(3000, () => {
  console.log("SV is running!!!");
});
