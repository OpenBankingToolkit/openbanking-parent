[<img src="https://raw.githubusercontent.com/ForgeRock/forgerock-logo-dev/master/forgerock-logo-dev.png" align="right" width="220px"/>](https://developer.forgerock.com/)

ForgeRock OpenBanking Parent
==========================

Maven parent used by the ForgeRock OpenBanking maven project.
It contains all the dependencies, their specific version and remote repositories.
The idea is to avoid having each project importing a different version of a library.

By sharing the same parent, we can assure a certain consistency between the different maven project