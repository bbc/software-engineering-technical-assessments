package api

import (
	"encoding/json"
	"fmt"
	"github.com/gorilla/mux"
	"log"
	"net/http"
	"strconv"
)

type ElectionApp struct {
	router  *mux.Router
	results ResultService
}

func NewElectionApp() *ElectionApp {
	router := mux.NewRouter()
	app := ElectionApp{router, NewResultService()}
	router.HandleFunc("/result", app.newResult).Methods("POST")
	router.HandleFunc("/result/{id:[0-9]+}", app.getResult).Methods("GET")
	router.HandleFunc("/scoreboard", app.getScoreboard).Methods("GET")
	return &app
}

func (a *ElectionApp) Run(port int) {
	log.Fatal(http.ListenAndServe(fmt.Sprintf(":%d", port), a.router))
}

type ConstituencyResult struct {
	Id           int           `json:"id"`
	Name         string        `json:"name"`
	SeqNo        int           `json:"seqNo"`
	PartyResults []PartyResult `json:"partyResults"`
}

type PartyResult struct {
	Party string      `json:"party"`
	Votes uint        `json:"votes"`
	Share json.Number `json:"share"`
}

type Scoreboard struct {
}

func (a ElectionApp) newResult(writer http.ResponseWriter, request *http.Request) {
	var cr ConstituencyResult
	decoder := json.NewDecoder(request.Body)
	defer request.Body.Close()
	err := decoder.Decode(&cr)
	if err != nil {
		a.writeResponse(writer, 500, err.Error())
		return
	}
	err = a.results.AddResult(cr)
	if err != nil {
		a.writeResponse(writer, 500, err.Error())
		return
	}
	a.writeResponse(writer, 201, "Created")
}

func (a ElectionApp) getResult(writer http.ResponseWriter, request *http.Request) {
	vars := mux.Vars(request)
	id, err := strconv.Atoi(vars["id"])
	if err != nil {
		a.writeResponse(writer, 400, fmt.Sprintf("result: %s is not an int", vars["id"]))
		return
	}
	result, err := a.results.GetResult(id)
	if err != nil {
		_, ok := err.(NotFound)
		if ok {
			a.writeResponse(writer, 404, err.Error())
		} else {
			a.writeResponse(writer, 500, err.Error())
		}
		return
	}
	status := 200
	response, _ := json.Marshal(result)

	writer.Header().Set("Content-Type", "application/json")
	writer.WriteHeader(status)
	writer.Write(response)
}

func (a ElectionApp) getScoreboard(writer http.ResponseWriter, request *http.Request) {
	response := []byte("")

	writer.Header().Set("Content-Type", "application/json")
	writer.WriteHeader(200)
	writer.Write(response)
}

func (a ElectionApp) writeResponse(writer http.ResponseWriter, status int, body string) {
	writer.WriteHeader(status)
	writer.Write([]byte(body))
}
