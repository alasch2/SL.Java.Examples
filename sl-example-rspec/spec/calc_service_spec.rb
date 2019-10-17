require 'sealights-rspec-agent'
require 'rest-client'
require 'json'

RSpec.describe 'Calculator Service' do
  it 'should add two numbers 3 + 2 = 5' do
    response = RestClient.post "http://localhost:8080/calculation", "{\"binaryOperation\": \"ADDITION\", \"leftOperand\": 3, \"rightOperand\": 2}", :content_type => :json
    expect(JSON.parse(response)['result']).to eq 5
  end

  it 'should multiply two numbers 3.5 * 4 = 14' do
    response = RestClient.post "http://localhost:8080/calculation", "{\"binaryOperation\": \"MULTIPLICATION\", \"leftOperand\": 3.5, \"rightOperand\": 4}", :content_type => :json
    expect(JSON.parse(response)['result']).to eq 14
  end

  it 'should subtract two numbers 1 - 3 = -2' do
    response = RestClient.get "http://localhost:8080/subtraction?leftOperand=1&rightOperand=3", :content_type => :json
    expect(JSON.parse(response)['result']).to eq -2
  end

end