file-manager
│
├── .idea/                          
├── pom.xml                         # Maven project file
├── src
│   └── main
│       ├── java
│       │   └── com.example.filemanager
│       │       ├── controller
│       │       │   └── FileController.java      # REST endpoints for file operations
│       │       ├── model
│       │       │   └── FileEntity.java          # JPA entity representing files
│       │       ├── repository
│       │       │   └── FileRepository.java      # Spring Data repository for CRUD
│       │       ├── service
│       │       │   └── FileCleanupService.java # Scheduled deletion / file cleanup
│       │       └── FileManagerApplication.java # Main Spring Boot application class
│       │
│       └── resources
│           └── application.properties          # Spring Boot configuration
