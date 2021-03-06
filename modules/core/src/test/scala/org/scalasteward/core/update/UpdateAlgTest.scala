package org.scalasteward.core.update

import org.scalasteward.core.TestSyntax._
import org.scalasteward.core.data.{ArtifactId, Update}
import org.scalasteward.core.util.Nel
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class UpdateAlgTest extends AnyFunSuite with Matchers {

  test("isUpdateFor") {
    val dependency = "io.circe" % ArtifactId("circe-refined", "circe-refined_2.12") % "0.11.2"
    val update = Update.Group(
      Nel.of(
        "io.circe" % ArtifactId("circe-core", "circe-core_2.12") % "0.11.2",
        "io.circe" % Nel.of(
          ArtifactId("circe-refined", "circe-refined_2.12"),
          ArtifactId("circe-refined", "circe-refined_sjs0.6_2.12")
        ) % "0.11.2"
      ),
      Nel.of("0.12.3")
    )
    UpdateAlg.isUpdateFor(update, dependency) shouldBe true
  }
}
