from flask import Flask
import json

app = Flask(__name__)

@app.route("/result/<id>", methods=["GET"])
def individual_result(id):
    return f"/result/{id}"

@app.route("/result", methods=["POST"])
def add_result():
    return "/result"

@app.route("/scoreboard", methods=["GET"])
def scoreboard():
    return "/scoreboard"