import unittest
import json
import os

from server import app, controller

class TestScoreboard(unittest.TestCase):

    def __init__(self, methodName: str = ...) -> None:
        super().__init__(methodName)
        self.server = app.test_client(self)
        file_dir: str = os.path.dirname(os.path.realpath(__file__))
        self.RESULT_SAMPLE_PATH: str = f"{file_dir}/resources/sample-election-results"

    def load_and_post_result_file(self, num: str) -> dict:
        file_number: str = str(num).zfill(3)
        with open(f"{self.RESULT_SAMPLE_PATH}/result{file_number}.json", "r", encoding="utf-8") as file:
            result = file.read()
        return self.server.post("/result", json=json.loads(result))

    def load_results(self, quantity: int) -> list[dict]:
        results = []
        for i in range(quantity):
            results.append(self.load_and_post_result_file(i + 1))
        return results

    def fetch_scoreboard(self) -> tuple[int, dict]: # returns (status_code, response_body_object)
        response = self.server.get("/scoreboard")
        return (response.status_code, json.loads(response.data.decode("utf-8")))

    def setUp(self) -> None:
        controller.reset()

    def test_first_5(self) -> None:
        self.load_results(5)
        status_code, scoreboard = self.fetch_scoreboard()

        if status_code != 200:
            self.fail(f"non-200 status code received: {status_code}")

        self.assertNotEqual(len(scoreboard), 0)
        # assert LD == 1
		# assert LAB = 4
		# assert winner = noone

    def test_first_100(self) -> None:
        self.load_results(100)
        status_code, scoreboard = self.fetch_scoreboard()

        if status_code != 200:
            self.fail(f"non-200 status code received: {status_code}")

        self.assertNotEqual(len(scoreboard), 0)
        # assert LD == 12
		# assert LAB == 56
		# assert CON == 31
		# assert winner = noone

    def test_first_554(self) -> None:
        self.load_results(554)
        status_code, scoreboard = self.fetch_scoreboard()

        if status_code != 200:
            self.fail(f"non-200 status code received: {status_code}")

        self.assertNotEqual(len(scoreboard), 0)
        # assert LD == 52
		# assert LAB = 325
		# assert CON = 167
		# assert winner = LAB

    def test_all_results(self) -> None:
        self.load_results(650)
        status_code, scoreboard = self.fetch_scoreboard()

        if status_code != 200:
            self.fail(f"non-200 status code received: {status_code}")

        self.assertNotEqual(len(scoreboard), 0)
        # assert LD == 62
		# assert LAB == 349
		# assert CON == 210
		# assert winner = LAB
		# assert sum = 650

if __name__ == "__main__":
    unittest.main()
