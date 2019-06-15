import mill._, scalalib._


trait AppModule extends ScalaModule{
  def scalaVersion = "2.13.0"
  def ivyDeps = Agg(
    ivy"com.lihaoyi::cask:0.1.9",
  )

  def forkWorkingDir = build.millSourcePath

  object test extends Tests{
    def testFrameworks = Seq("utest.runner.Framework")

    def ivyDeps = Agg(
      ivy"com.lihaoyi::utest::0.6.9",
      ivy"com.lihaoyi::requests::0.2.0",
    )

    def forkWorkingDir = build.millSourcePath

    // redirect this to the forked `test` to make sure static file serving works
    def testLocal(args: String*) = T.command{
      test(args:_*)
    }
  }
}