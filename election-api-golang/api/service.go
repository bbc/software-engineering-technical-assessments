package api

import "fmt"

type ResultService interface {
	AddResult(result ConstituencyResult) error
	GetResult(id int) (ConstituencyResult, error)
	GetAll() ([]ConstituencyResult, error)
	Reset() error
}

func NewResultService() ResultService {
	return mapResultService{
		make(map[int]ConstituencyResult, 0),
	}
}

type mapResultService struct {
	data map[int]ConstituencyResult
}

func (m mapResultService) Reset() error {
	m.data = make(map[int]ConstituencyResult, 0)
	return nil
}

func (m mapResultService) AddResult(result ConstituencyResult) error {
	m.data[result.Id] = result
	return nil
}

type NotFound string

func (n NotFound) Error() string {
	return fmt.Sprintf("%s resource not found", string(n))
}

func (m mapResultService) GetResult(id int) (ConstituencyResult, error) {
	res, ok := m.data[id]
	if !ok {
		return ConstituencyResult{}, NotFound(fmt.Sprintf("%d", id))
	}
	return res, nil
}

func (m mapResultService) GetAll() ([]ConstituencyResult, error) {
	var results []ConstituencyResult
	for _, v := range m.data {
		results = append(results, v)
	}
	return results, nil
}
