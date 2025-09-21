import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-chatbot-widget',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './chatbot-widget.html',
  styleUrls: ['./chatbot-widget.css']
})
export class ChatbotWidgetComponent {
  isOpen = false;
  messages: {text: string, isUser: boolean}[] = [];
  isTyping = false;
  language = 'hi';

  constructor(private http: HttpClient) {
    this.messages.push({
      text: 'नमस्ते! मैं आपका weather assistant हूं। 🌤️ पूछिए weather के बारे में कुछ भी!',
      isUser: false
    });
  }

  toggleChat() {
    this.isOpen = !this.isOpen;
  }

  async sendMessage(input: HTMLInputElement) {
    const message = input.value.trim();
    if (!message) return;
    
    this.messages.push({text: message, isUser: true});
    input.value = '';
    this.isTyping = true;

    try {
      const response = await this.http.post<{response: string}>('http://localhost:8085/api/chat/message', 
        {message, language: this.language}).toPromise();
      
      this.isTyping = false;
      this.messages.push({text: response?.response || 'Sorry, कुछ problem है।', isUser: false});
    } catch (error) {
      this.isTyping = false;
      this.messages.push({text: 'Sorry, अभी chatbot available नहीं है।', isUser: false});
    }
  }

  onKeyPress(event: KeyboardEvent, input: HTMLInputElement) {
    if (event.key === 'Enter') {
      this.sendMessage(input);
    }
  }

  toggleLanguage() {
    this.language = this.language === 'hi' ? 'en' : 'hi';
    const welcomeMsg = this.language === 'hi' 
      ? 'नमस्ते! मैं आपका weather assistant हूं। 🌤️ पूछिए weather के बारे में कुछ भी!'
      : 'Hello! I am your weather assistant. 🌤️ Ask me anything about weather!';
    this.messages = [{text: welcomeMsg, isUser: false}];
  }
}