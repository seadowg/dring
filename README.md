```kotlin

fun SearchView.getQueries(): Source<String> {
  val queries = Source<String>()
  searchView.addOnQueryTextListener(QueryTextListener() {
    fun onQueryTextChanged(query: String) {
      queries.push(query)
    }

    fun onQueryTextSubmitted(query: String) {
      queries.push(query)
    }
  })

  return queries
}

class ResultsAdapter(val results: Source<List<String>>): Sink<List<String>> {
  init {
    results.bind(this)
  }

  fun process(results: List<String?) {
    setResults(results)
  }
}

val queries = searchView.getQueries()
val results = searcher.searchFor(queries)
resultsList.setAdapter(new ResultsAdapter(results))
```
