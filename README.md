# JFract4D

Infraction manager for Discord server administrators and moderators

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

a SQL Database of any kind (MariaDB, MySQL, MS SQL etc...)

### Installing

First off you'll need to install your SQL Server of choice and run this [SQL Script](database.sql) to set up the structure. You may also run [this script](dummy.sql) that adds dummy data in the database for testing purposes.

After that change the data in [jfract.properties](jfract.properties) to fit your server configuration

And that's it! You should be good to go.

## License

This project is licensed under the Apache-2.0 license - see the [LICENSE](LICENSE) file for details