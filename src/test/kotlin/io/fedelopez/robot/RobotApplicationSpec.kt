package io.fedelopez.robot

import io.kotlintest.shouldBe
import io.kotlintest.specs.DescribeSpec

class RobotApplicationSpec : DescribeSpec() {
    init {
        describe("RobotApplicationSpec") {
            it("moves the boxes") {
                val commands = "PMLPMMMLPMLPMML"
                val expected = "0211000000"
                val move = move(commands)
                move.shouldBe(expected)
            }

            it("lowering on a pile of 15 does nothing") {
                val commands = "PLPLPLPLPLPLPLPLPLPLPLPLPLPLPLPL"
                val expected = "f000000000"
                val move = move(commands)
                move.shouldBe(expected)
            }

            it("does not go beyond position 9") {
                val commands = "PMMMMMMMMMMLPL"
                val expected = "1000000000"
                val move = move(commands)
                move.shouldBe(expected)
            }

            it("lowering without a block does nothing") {
                val commands = "LLL"
                val expected = "0000000000"
                val move = move(commands)
                move.shouldBe(expected)
            }
        }
    }
}
