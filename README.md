# Cabinet-Medical-base-de-donne

## Overview
This project is a comprehensive database management system designed for a medical cabinet. It facilitates the efficient management of patient records, appointments, medical history, and other related information. The system aims to streamline operations within a medical practice, ensuring data integrity and accessibility.

## Features
- **Patient Management**: Maintain detailed records of patients including personal information, medical history, and contact details.
- **Appointment Scheduling**: Efficiently schedule and manage appointments between patients and doctors.
- **Doctor Management**: Keep track of doctor details, their specializations, and availability.
- **Medical Records**: Store and retrieve detailed medical records and treatment histories.
- **User Authentication**: Secure login system for different user roles (admin, doctor, receptionist).
- **Reporting**: Generate reports for patient visits, treatments, and other metrics.

## Technologies Used
- **Backend**: Java, Spring Boot
- **Frontend**: HTML, CSS, JavaScript
- **Database**: MySQL
- **Build Tool**: Maven
- **Version Control**: Git

## Directory Structure
```
Cabinet-Medical-base-de-donne/
├───.vscode
├───app
└───cabinet
    ├───.vscode
    ├───bin
    │   ├───Cabinet
    │   │   ├───Management
    │   │   └───Personnels
    │   ├───database
    │   ├───GUI
    │   ├───GUI_Advance
    │   ├───Main_classes
    │   ├───org
    │   └───Website
    │       ├───css
    │       ├───images
    │       └───js
    └───src
        ├───Cabinet
        │   ├───Management
        │   └───Personnels
        ├───database
        ├───GUI
        ├───GUI_Advance
        ├───Main_classes
        ├───org
        └───Website
            ├───css
            ├───images
            └───js

└── README.md
```

## Database
The project uses a relational database to store and manage data. The database schema includes tables for patients, doctors, appointments, and medical records.

### Database Schema
- **Patients**: Stores patient information such as name, age, contact details, and medical history.
- **Doctors**: Stores doctor information including specialization, contact details, and availability.
- **Appointments**: Manages appointment scheduling between patients and doctors.
- **Medical Records**: Contains detailed medical history and treatment records of patients.



## Installation
To set up the project locally, follow these steps:

1. **Clone the repository**:
    ```bash
    git clone https://github.com/rayan3230/Cabinet-Medical-base-de-donne.git
    cd Cabinet-Medical-base-de-donne
    ```

2. **Set up the database**:
    - Install MySQL and create a database named `cabinet_medical`.
    - Update the database configuration in `src/`.



## Contributing
We welcome contributions to enhance the project. To contribute, follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes and commit them (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a pull request.



## Contact
For any inquiries or support, please contact us at support@cabinetmedical.com.

## Images
Below are some images representing the database schema and application interface:

### Database Schema Diagram
![Database Schema](cabinet\src\Website\images\database.png)

### Application Interface
![Application Interface](cabinet\src\Website\images\dashboard.png)