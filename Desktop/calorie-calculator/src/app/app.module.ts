import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { CalorieCalculatorComponent } from './components/calorie-calculator/calorie-calculator.component';

const routes: Routes = [
  { path: '', component: CalorieCalculatorComponent }
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatSelectModule,
    RouterModule.forRoot(routes),
    CalorieCalculatorComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
