import { isLogin, formatDate } from '../../main/utils';

test('isLogin should always return true', () => {
  const login = isLogin();
  expect(login).toBe(true);
});

test('formatDate should return a date string in MM/dd/YYYY hh:mm format', () => {
  const formated = formatDate("2020-11-22 12:34");
  expect(formated).toBe("11/22/2020 12:34");
});

test('formatDate should return null if the input is null', () => {
  const formated = formatDate(null);
  expect(formated).toBe(null);
});