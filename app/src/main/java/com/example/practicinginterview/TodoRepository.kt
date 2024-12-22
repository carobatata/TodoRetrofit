import com.example.practicinginterview.api.RetrofitInstance
import com.example.practicinginterview.api.TodoApi
import com.example.practicinginterview.api.TodoModel

class TodoRepository {
    private val api = RetrofitInstance.api

    suspend fun getAllTodos(): Result<List<TodoModel>?> {
        return try {
            val response = api.getAllTodos()
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Exception(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}