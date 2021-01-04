[<img src="https://raw.githubusercontent.com/ForgeRock/forgerock-logo-dev/master/Logo-fr-dev.png" align="right" width="220px"/>](https://developer.forgerock.com/)

| |Current Status|
|---|---|
|Build|[![Build Status](https://img.shields.io/endpoint.svg?url=https%3A%2F%2Factions-badge.atrox.dev%2FOpenBankingToolkit%2Fopenbanking-parent%2Fbadge%3Fref%3Dmaster&style=flat)](https://actions-badge.atrox.dev/OpenBankingToolkit/openbanking-parent/goto?ref=master)|
|Code coverage|[![codecov](https://codecov.io/gh/OpenBankingToolkit/openbanking-parent/branch/master/graph/badge.svg)](https://codecov.io/gh/OpenBankingToolkit/openbanking-parent)|
|Release|[![GitHub release (latest by date)](https://img.shields.io/github/v/release/OpenBankingToolkit/openbanking-parent.svg)](https://img.shields.io/github/v/release/OpenBankingToolkit/openbanking-parent)|
|License|![license](https://img.shields.io/github/license/ACRA/acra.svg)|

**_This repository is part of the Open Banking Tool kit. If you just landed to that repository looking for our tool kit,_
_we recommend having a first read to_ https://github.com/OpenBankingToolkit/openbanking-toolkit**


ForgeRock OpenBanking Parent
============================

Maven parent used by the ForgeRock OpenBanking maven project.
It contains all the dependencies, their specific version and remote repositories.
The idea is to avoid having each project importing a different version of a library.

By sharing the same parent, we can assure a certain consistency between the different maven project
