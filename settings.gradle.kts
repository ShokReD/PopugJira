rootProject.name = "popug-jira"

include("common")

include("event")

include("task:domain")
include("task:use-case")
include("task:persistence")

include("account:domain")
include("account:use-case")
include("account:persistence")
