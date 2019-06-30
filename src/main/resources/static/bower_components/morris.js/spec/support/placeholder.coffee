beforeEach ->
  placeholder = $('<div code="graph" style="width: 600px; height: 400px"></div>')
  $('#test').append(placeholder)

afterEach ->
  $('#test').empty()
