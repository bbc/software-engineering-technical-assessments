import unittest, json
from server import app, controller

class TestScoreboard(unittest.TestCase):
    @classmethod
    def setUpClass(self) -> None:
        self.client = app.test_client(self)

    def setUp(self) -> None:
        controller.reset()

    def fetch_scoreboard(self) -> list[dict]:
        response = self.client.get("/scoreboard")
        return json.loads(response.get_data(as_text=True))

    def test_should_count_total_seats_per_party(self) -> None:
        self.client.post('/result', json={
            "id": 1,
            "name": "Aberconwy",
            "partyResults": [
                { "party": "LAB", "votes": 3 },
                { "party": "CON", "votes": 2 },
                { "party": "LD", "votes": 1 },
            ]
        })

        self.client.post('/result', json={
            "id": 2,
            "name": "Arfon",
            "partyResults": [
                { "party": "LAB", "votes": 3 },
                { "party": "CON", "votes": 2 },
                { "party": "LD", "votes": 1 },
            ]
        })

        self.client.post('/result', json={
            "id": 3,
            "name": "Basildon South \u0026 East Thurrock",
            "partyResults": [
                { "party": "LD", "votes": 3 },
                { "party": "CON", "votes": 2 },
                { "party": "LAB", "votes": 1 },
            ]
        })

        scoreboard = self.fetch_scoreboard()

        # Add your assertions here.
        # * Assert that LAB have won 2 seats
        # * Assert that LD have won 1 seat

    def test_should_report_no_winner_when_majority_not_reached(self) -> None:
        labour_seat_count = 324
        seats_declared_so_far = 400

        for i in range(labour_seat_count):
            self.client.post('/result', json={
                "id": i,
                "name": f"Constituency {i}",
                "partyResults": [
                    { "party": "LAB", "votes": 3 },
                    { "party": "CON", "votes": 2 },
                    { "party": "LD", "votes": 1 },
                ]
            })
        
        for i in range(labour_seat_count, seats_declared_so_far):
            self.client.post('/result', json={
                "id": i,
                "name": f"Constituency {i}",
                "partyResults": [
                    { "party": "CON", "votes": 3 },
                    { "party": "LD", "votes": 2 },
                    { "party": "LAB", "votes": 1 },
                ]
            })
        
        scoreboard = self.fetch_scoreboard()

        ### Add your assertions here. ###
        # * Assert that there's no winner yet

    def test_should_report_winner_when_majority_reached(self) -> None:
        # LAB have won 325 seats, they have a majority (even if not all constituencies declared yet)
        for i in range(325):
            self.client.post('/result', json={
                "id": i,
                "name": f"Constituency {i}",
                "partyResults": [
                    { "party": "LAB", "votes": 3 },
                    { "party": "CON", "votes": 2 },
                    { "party": "LD", "votes": 1 },
                ]
            })
        
        # CON have won the rest of the seats reported so far
        for i in range(325, 400):
            self.client.post('/result', json={
                "id": i,
                "name": f"Constituency {i}",
                "partyResults": [
                    { "party": "CON", "votes": 3 },
                    { "party": "LD", "votes": 2 },
                    { "party": "LAB", "votes": 1 },
                ]
            })
        
        scoreboard = self.fetch_scoreboard()

        ### Add your assertions here. ###
        # * Assert that there's no winner yet

    # Add more test cases here

if __name__ == "__main__":
    unittest.main()