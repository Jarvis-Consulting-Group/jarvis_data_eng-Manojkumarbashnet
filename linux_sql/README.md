# Introduction
This Project is basically designing and implementing an MVP(minimum viable product) that helps the LCA(Linux Cluster Administration ) to meet thier business requirements.cluster monitor use Bash ,docker,gita and PostgreSQL technologies

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



## Architecture
Draw a cluster diagram with three Linux hosts, a DB, and agents (use draw.io website). Image must be saved to the `assets` directory.

## Scripts
Shell script description
- psql_docker.sh 

- host_info.sh
- host_usage.sh
- crontab
- queries.sql (describe what business problem you are trying to resolve)

## Database Modeling
Describe the schema of each table using markdown table syntax (do not put any sql code)
- `host_info`
- `host_usage`

# Test
How did you test your bash scripts DDL? What was the result?

# Deployment
How did you deploy your app? (e.g. Github, crontab, docker)

# Improvements 
- Update the device specifications to add a more functionality to handle hardware changes. 
- More 
- Create a GUI for easier use and a straightforward view.