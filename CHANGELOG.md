# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.1.1] - 2023-07-21

### Added
    - added README.md
    - added CHANGELOG.md
    - added getters and setters addnotations to employees entites

### Fixed
    - used PutMapping in updateEmployee, not PostMapping
    - returned EmployeeResponse in getAllEmployees function in EmployeeController,
      not ResponseEntity
    - returned EmployeeResponse in getAllemployees function in EmployeeService

### Changed
    - rafactored endpoints accroding to REST principles

### Removed
    - removed old JUnit dependency
    - removed unnecessary line in pom.xml
