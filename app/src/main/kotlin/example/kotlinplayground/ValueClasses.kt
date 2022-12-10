package example.kotlinplayground

// 色々な値オブジェクトを見ていきましょう！ ＼＼\\٩( 'ω' )و //／／

// 値の要件
// - nullable
// - 前後の空白は取り除く
// - "" や "   " は nullにする

// validationとかはinitでチェックする

// -------------------------------------------------------------------------
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
//	userName1.value = "　谷田 一樹　"
//	println(userName1.value) // 　谷田 一樹　
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
CASE3: data class, private constructor, valプロパティ

private constructorでprimary constructor呼び出しでインスタンス生成させない様にしたが、
data classはcopyでprivate constructorを公開してしまう問題がある。
↓
Private primary constructor is exposed
via the generated 'copy()' method of a 'data' class.
*/
//data class UserName private constructor(val value: String?) {
//    companion object {
//        operator fun invoke(value: String?): UserName {
//            return UserName(
//                value = value?.let { it.ifBlank { null }?.trim() }
//            )
//        }
//    }
//}
//
//fun main() {
//    val userName1 = UserName("　谷田 一樹　")
//    println(userName1.value) // 谷田 一樹
//
//    val userName2 = userName1.copy("　谷田 一樹　")
//    println(userName2.value) // 　谷田 一樹　
//}

// -------------------------------------------------------------------------
/*
CASE4: value class(inline class), private constructor, operator invoke(or factory method)

value classはvarは使えないので再代入される心配は無い。
value classはcopyメソッドが無いのでprivate constructorにしたらprimary constructorを公開することは無い。
実際はラインタイム時にUserNameクラスのインスタンスは作成されず、Stringとして扱われるのでオーバーヘッドが少ない。
*/

@JvmInline
value class UserName private constructor(val value: String?) {
	init {
		println("200文字超えNGとかのvalidationチェックする")
	}

	companion object {
		operator fun invoke(value: String?): UserName {
			return UserName(
				value = value?.let { it.ifBlank { null }?.trim() }
			)
		}

		fun new(value: String?): UserName {
			return UserName(
				value = value?.let { it.ifBlank { null }?.trim() }
			)
		}
	}
}

fun main() {
	val userName1 = UserName("　谷田 一樹　") // operator invokeで呼び出し
	println(userName1.value) // "谷田 一樹"

	val userName2 = UserName.new("　　") // factoryメソッドで呼び出し
	println(userName2.value) // null
}

// -------------------------------------------------------------------------
//ただ、value classは一部のJSONシリアライズライブラリやmockitoなどが対応していないこともあるらしい...
