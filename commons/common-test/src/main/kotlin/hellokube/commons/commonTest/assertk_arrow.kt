package hellokube.commons.commonTest

import arrow.core.Either
import assertk.Assert
import assertk.assertions.isEqualTo

fun <L, R> Assert<Either<L, R>>.isRight(): R {
    given {
        assertThat(it.isRight()).isEqualTo(true)
        return (it as Either.Right).value
    }
    throw Exception("Unreachable")
}

fun <L, R> Assert<Either<L, R>>.isLeft(): L {
    given {
        assertThat(it.isLeft()).isEqualTo(true)
        return (it as Either.Left).value
    }
    throw Exception("Unreachable")
}
