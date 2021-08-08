import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HompPageComponent } from './homp-page.component';

describe('HompPageComponent', () => {
  let component: HompPageComponent;
  let fixture: ComponentFixture<HompPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HompPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HompPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
