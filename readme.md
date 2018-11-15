# Simple balanced team generator

## Scope

Have N partecipants,you need to generate balanced teams based on a score.

## How it works

This software generate balanced teams of N partecipants, given a score for each, using the simulated annealing technique.

A simple standalone demo is provided: build the project and run

_java -jar TeamCreator.jar_

to run the program with random generated values.

##Configuration and customization

Euristic parameters are set in the Config.java class, provided by a _DataSource_.

Configuration parameters (Config class):

    * teamSize: Size of the teams
    * temperature: parameter of the simulated annealing, default: 100
    * rate: parameter of the simulated annealing, default: 0.02

The _DataSource_ interface can be implemented to provide different ways to fetch data: an example _FileDataSource_ is provided.

The _Report_ interface is used to implement a class to export the generated data:
 
 `generateReport(List<Group<Item>> list, Double target, Double distance)` 
is called at the end of the simulation: each _Group<Item>_ represents a balanced team, _target_ is the average score of all partecipants and _distance_ is the maximum difference between average scores of the groups.
 
An example report _PrintOutputReport_,which prints to the standard output, is provided.

Partecipants model needs to implement the _Item_ interface and provide a clone() method, a simple _Partecipant_ class is provided.

## Embedding

```
TeamCreator creator = new TeamCreator();
DataSource ds = new ... ;
Report report = new ... ;
creator.run(ds,report);
```

`DataSource.getConfig()` is invoked to fetch data and configuration values, `report.generateReport()` is used at the end of the simulation to generate a result. 
