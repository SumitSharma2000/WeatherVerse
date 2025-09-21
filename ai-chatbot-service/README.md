# 🤖 WeatherVerse AI Chatbot

Natural language weather assistant that understands Hindi/English queries.

## Features
- Natural language processing for weather queries
- Hindi/English mixed language support
- Activity suggestions based on weather
- Simple chat interface

## Sample Queries
- "Kal Mumbai me barish hogi kya?"
- "Mujhe running ke liye best time suggest karo"
- "Delhi me aaj mausam kaisa hai?"

## Setup
1. `mvn clean install`
2. `mvn spring-boot:run`
3. Open http://localhost:8085

## API Endpoint
- POST `/api/chat/message` - Send chat message
  ```json
  {
    "message": "Mumbai me barish hogi kya?"
  }
  ```