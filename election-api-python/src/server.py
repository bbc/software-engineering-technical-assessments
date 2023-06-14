from flask import Flask, request
from results_controller import ResultsController

app: Flask = Flask(__name__)
controller: ResultsController = ResultsController()

@app.route("/result/<id>", methods=["GET"])
def individual_result(id) -> dict:
    return controller.get_result(int(id))

@app.route("/result", methods=["POST"])
def add_result() -> dict:
    return controller.new_result(request.json)

@app.route("/scoreboard", methods=["GET"])
def scoreboard() -> dict:
    return controller.scoreboard()
