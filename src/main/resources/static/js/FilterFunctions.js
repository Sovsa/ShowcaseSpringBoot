function subdomainFilter(input) {
	var regex = /[^a-z0-9-]/gi
	input.value = input.value.replace(regex, '')
}