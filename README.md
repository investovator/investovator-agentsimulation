This is an ongoing attempt to model a stock market using the [JASA](http://jasa.sourceforge.net/) framework. Currently the project,
* Does the simulations according to the stock market conventions (JASA does the simulations adhering to the auction conventions).
* Allows simulation of multiple markets (stocks).
* Allows human players to interact with the simulation by placing orders (buy/ask).

# How to run the project..?
##Using maven
Run the following commands.
* **mvn package -Dmaven.test.skip=true**
* **mvn exec:java**

## Using your IDE
Create a run configuration in your IDE as follows.
* Class to be run - **org.investovator.Main**
* VM options -  **-Djabm.config=./examples/multiasset/main.xml**
