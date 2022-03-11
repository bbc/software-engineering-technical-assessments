from flask import Flask, request
from results_controller import ResultsController
import json

app = Flask(__name__)
controller = ResultsController()

@app.route("/result/<id>", methods=["GET"])
def individual_result(id):
    return controller.get_result(int(id))

@app.route("/result", methods=["POST"])
def add_result():
    return controller.new_result(request.json)

@app.route("/scoreboard", methods=["GET"])
def scoreboard():
    return controller.scoreboard()

# Debugging only
@app.route("/debug", methods=["GET"])
def debug():
    return str(controller.get_all()).replace("'", '"')