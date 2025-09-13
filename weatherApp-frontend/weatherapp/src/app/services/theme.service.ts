import { Injectable, signal, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

export type Theme = 'light' | 'dark' | 'night';

@Injectable({ providedIn: 'root' })
export class ThemeService {
  private readonly THEME_KEY = 'weather-app-theme';
  
  currentTheme = signal<Theme>('light');

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {
    if (isPlatformBrowser(this.platformId)) {
      this.loadTheme();
      this.applyTheme(this.currentTheme());
    }
  }

  private loadTheme(): void {
    const savedTheme = localStorage.getItem(this.THEME_KEY) as Theme;
    if (savedTheme && ['light', 'dark', 'night'].includes(savedTheme)) {
      this.currentTheme.set(savedTheme);
    }
  }

  private saveTheme(theme: Theme): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem(this.THEME_KEY, theme);
    }
  }

  private applyTheme(theme: Theme): void {
    if (isPlatformBrowser(this.platformId)) {
      document.documentElement.setAttribute('data-theme', theme);
      document.body.className = `theme-${theme}`;
    }
  }

  setTheme(theme: Theme): void {
    this.currentTheme.set(theme);
    this.saveTheme(theme);
    this.applyTheme(theme);
  }

  toggleTheme(): void {
    const themes: Theme[] = ['light', 'dark', 'night'];
    const currentIndex = themes.indexOf(this.currentTheme());
    const nextIndex = (currentIndex + 1) % themes.length;
    this.setTheme(themes[nextIndex]);
  }

  getThemeIcon(): string {
    switch (this.currentTheme()) {
      case 'light': return '☀️';
      case 'dark': return '🌙';
      case 'night': return '🌃';
      default: return '☀️';
    }
  }

  getThemeLabel(): string {
    switch (this.currentTheme()) {
      case 'light': return 'Light';
      case 'dark': return 'Dark';
      case 'night': return 'Night';
      default: return 'Light';
    }
  }
}
