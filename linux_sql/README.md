# Introduction
A Linux clustering agent is a minimal viable product that seeks to keep track of the resources consumed by each node in the cluster as well as their hardware requirements.In this project, a psql instance is created using docker, and bash scripts are then used to perform commands to obtain hardware information and node memory utilisation.
By creating a PostgreSQL database connection, the data collected by bash scripts is added to the PostgreSQL database.
Finally, with the use of crontab, a bash script containing data on resource utilisation is programmed to execute once per minute in order to obtain real-time records.This Project is basically designing and implementing an MVP(minimum viable product) that helps the LCA(Linux Cluster Administration ) to meet thier business requirements.cluster monitor use Bash ,docker,gita and PostgreSQL technologies

## Who are the users?
Anyone who needs to get satistical and realtional data about the CPU using Psql by performing perform various operations such as inserting, updating, retrieving, and deleting data.

# Quick Start
Use markdown code block for your quick-start commands
- Start a psql instance using psql_docker.sh
- Create tables using ddl.sql
- Insert hardware specs data into the DB using host_info.sh
- Insert hardware usage data into the DB using host_usage.sh
- Crontab setup

# Implemenation
1. Created and configured a Linux server on Google Cloud Platform as CentOS 7  
2. Installed Docker and created a PostgreSQL Docker container using the PostgreSQL:10 image from the Docker Hub.  
3. Installed the PSQL client and setup the PSQL database.  
	- Created the "host_agent" database to store data for the project.  
	- Created the "host_info" table to store the hardware specification  
	- Created the "host_usage" table to store real-time server usage for each host.  
4. Created scripts for collecting corresponding data and added it to the database.  
5. Used Crontab to automate the "host_usage" script to update the "host_usage" table every minute.


## Architecture
![pic.png](assets%2Flinux.png)

## Scripts
Shell script description
- psql_docker.sh 
  - `Usage`: This script creates, starts, or stops a  psql instance in docker.
- host_info.sh
  - `Usage`: This script collects hardware specifications in respective environment variables and inserts data into the host_info table.
- host_usage.sh
  -  `Usage`:This script collects resource usage in respective environment variables and inserts data into the host_usage table
- crontab
  - `Usage`: Crontab is a job scheduler that automated host_usage.sh to run every minute to collect real-time resource usage.
- ddl.sql
  - `Usage`: This file creates tables named host_info and host_usage if it does not exist on the system to save time and increase code reusability.

## Database Modeling
Describe the schema of each table using markdown table syntax (do not put any sql code)
- `host_info`
- `host_usage`

# Test
The scripts were tested on psql cli tool. The Bash -x command expands the output and prints each line in the terminal to track the exact location of the error.

# Deployment
The host usage script was automated every minute and used to deploy the programme on the Jarvis remote desktop using the Crontab scheduler.
To make a database connection and save data, the PostgreSQL client utilising Docker was developed and set up.
Git was used for version control, and all of the scripts were published to GitHub. 

# Improvements 
- Update the device specifications to add a more functionality to handle hardware changes. 
- Create a GUI for easier use and a straightforward view.