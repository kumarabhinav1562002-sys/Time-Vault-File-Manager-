# Time-based File Manager UI

Angular UI for a time-based file manager backend. Upload, download, and auto-expire files. Connects to a Spring Boot backend at `http://localhost:8080/files` by default.

## Features
- Upload files with optional expiry
- List, download, and delete files
- Shows expiry status (active/expired)

## Quick Start
1. Run `npm install`
2. Run `npm start`
3. Open [http://localhost:4200](http://localhost:4200)

## Configuration
- API URL: Edit `src/environments/environment.ts` if your backend runs elsewhere.
