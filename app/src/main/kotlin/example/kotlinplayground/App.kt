/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package example.kotlinplayground

// 色々な値オブジェクトを見ていきましょう！ ＼＼\\٩( 'ω' )و //／／

/*
CASE1: data class, varプロパティ, init

インスタンスの生成時にしかinitは呼ばれないし、
varなので再代入が可能。(不変でなくなってしまう。値自体は本来不変であるべき。)
*/

//data class UserName(var value: String?) {
//	init {
//	    value = value?.let { it.ifBlank { null }?.trim() }
//	}
//}
//
//fun main() {
//	val userName1 = UserName("　谷田 一樹　")
//	println(userName1.value) // 谷田 一樹
//
//	userName1.value = "　Kotlin 太郎　"
//	println(userName1.value) // 　Kotlin 太郎　
//}

// -------------------------------------------------------------------------
/*
CASE2: data class, valプロパティ, init

1つの値のためにpropertyを二つ用意している。
*/

//data class UserName(val input: String?) {
//	val value = input?.let { it.ifBlank { null }?.trim() }
//}
//
//fun main() {
//	val userName1 = UserName("　谷田 一樹　")
//	println(userName1.value) // 谷田 一樹
//}

// -------------------------------------------------------------------------
/*
CASE3: value class(inline class), private constructor, operator invoke

varは使えないので再代入される心配はない。
実際はラインタイム時にUserNameクラスのインスタンスは作成されず、Stringとして扱われるのでオーバーヘッドが少ない。
*/

//@JvmInline
//value class UserName private constructor(val value: String?) {
//	companion object {
//		operator fun invoke(value: String?): UserName {
//			return UserName(
//				value = value?.let { it.ifBlank { null }?.trim() }
//			)
//		}
//	}
//}
//
//fun main() {
//	val userName = UserName("　谷田 一樹　")
//	println(userName.value) // "谷田 一樹"
//}
