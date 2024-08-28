package main

import (
	"election/api"
	"log"
)

func main() {
	app := api.NewElectionApp()
	log.Printf("Starting, listning on: 8080")
	app.Run(8080)
}
