package example.kotlinplayground

// Userエンティティ
data class User(
    val id: UserId,
	val code: Code,
	val email: Email,
    val name: UserName,
)

fun main() {
	val user = User(
		id = UserId("1111"),
		code = Code("abc"),
		email = Email("a@a.a"),
		name = UserName(""),
	)

	println(user.name)
}

// ---------------------------------------------------------------------
// 各、値クラス

@JvmInline
value class UserId(val value: String) {
	// 何かロジックを持つ
}

@JvmInline
value class Email(val value: String) {
	// 何かロジックを持つ
}

@JvmInline
value class Code(val value: String) {
	// 何かロジックを持つ
}
