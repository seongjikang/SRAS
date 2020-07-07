import { SrasWwwPage } from './app.po';

describe('sras-www App', () => {
  let page: SrasWwwPage;

  beforeEach(() => {
    page = new SrasWwwPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
