import unittest, json, sys, os
testdir = os.path.dirname(__file__)
srcdir = "../src"
sys.path.insert(0, os.path.abspath(os.path.join(testdir, srcdir)))
from server import app, controller

class TestScoreboard(unittest.TestCase):

    def __init__(self, methodName: str = ...) -> None:
        super().__init__(methodName)
        self.server = app.test_client(self)
        self.RESULT_SAMPLE_PATH = "../test/resources/sample-election-results"

    def load_and_post_result_file(self, num: str) -> dict:
        file_number: str = str(num).zfill(3)
        with open(f"{self.RESULT_SAMPLE_PATH}/result{file_number}.json", "r") as file:
            result = file.read()
        return self.server.post("/result", json=json.loads(result))

    def load_results(self, quantity: int) -> list[dict]:
        results = []
        for i in range(quantity):
            results.append(self.load_and_post_result_file(i + 1))
        return results

    def setUp(self) -> None:
        controller.reset()

    @unittest.skip("_")
    def test_first_5(self) -> None:
        self.load_results(5)
        self.assertEqual(1, 1)

    @unittest.skip("_")
    def test_first_100(self) -> None:
        self.load_results(100)
    
    @unittest.skip("_")
    def test_first_554(self) -> None:
        self.load_results(554)

    @unittest.skip("_")
    def test_all_results(self) -> None:
        self.load_results(650)
    
    # Test test

    # def test(self) -> None:
    #     self.load_and_post_result_file(1)
    #     response = self.server.get("/debug")
    #     data = json.loads(str(response.data)[2:-1])
    #     print(data)


    #     print("\n" + "-"*100 + "\n")
    #     controller.reset()
    #     self.load_and_post_result_file(2)
    #     self.load_and_post_result_file(3)
    #     response = self.server.get("/debug")
    #     data = json.loads(str(response.data)[2:-1])
    #     print(data)

    # def test_bad_result_method(self) -> None:
    #     response = self.server.get("/result")
    #     status = response.status_code
    #     self.assertEqual(status, 405)

    # def test_scoreboard(self) -> None:
    #     response = self.server.get("/scoreboard")
    #     status = response.status_code
    #     self.assertEqual(status, 200)
    
  
if __name__ == "__main__":
    unittest.main()