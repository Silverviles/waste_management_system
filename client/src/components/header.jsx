import React from "react";
import { strings, colors } from "../common/Strings";
import {
  Popover,
  PopoverContent,
  PopoverHandler,
} from "@material-tailwind/react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

/**
 * This is the Header Component which is used to display the header of the application
 * @returns Header component
 */

export default function Header() {
  return (
    <div
      className="flex flex-row justify-between items-center px-4 py-2"
      style={{ backgroundColor: colors.primary }}
    >
      {/* Logo */}
      <div className="p-2 rounded-full">
        <img className="h-10" src={strings.logoImage} alt="Logo" />
      </div>

      {/* Header Navigation */}
      <div className="hidden sm:flex ">
        <ul className="flex space-x-4 font-semibold text-gray-800">
          <li className="hover:font-bold">Home</li>
          <li className="hover:font-bold">Services</li>
          <li className="hover:font-bold">About</li>
          <li className="hover:font-bold">Contact</li>
        </ul>
      </div>

      {/* Buttons */}
      <div className="flex items-center gap-2 mt-2 sm:mt-0">
        {/* Mobile Menu Button */}
        <div className="sm:hidden">
          <Popover placement="bottom-start">
            <PopoverHandler>
              <button className="p-1 border-2 rounded-full border-gray-800 hover:bg-gray-800 hover:text-gray-100">
                <FontAwesomeIcon className="h-4 w-6" icon="bars" />
              </button>
            </PopoverHandler>
            <PopoverContent className="rounded-3xl">
              <div>
                <ul className="p-2">
                  <li className="hover:font-semibold text-gray-800 pb-2">
                    Home
                  </li>
                  <li className="hover:font-semibold text-gray-800 pb-2">
                    Services
                  </li>
                  <li className="hover:font-semibold text-gray-800 pb-2">
                    About
                  </li>
                  <li className="hover:font-semibold text-gray-800">Contact</li>
                </ul>
              </div>
            </PopoverContent>
          </Popover>
        </div>
        <FontAwesomeIcon
          className="border-2 p-2 rounded-full border-gray-800 hover:bg-gray-800 hover:text-gray-100"
          icon="bell"
        />
        <Popover placement="bottom-start">
          <PopoverHandler>
            <button className="p-1 border-2 rounded-full border-gray-800 hover:bg-gray-800 hover:text-gray-100">
              <FontAwesomeIcon className="h-4 w-6" icon="user" />
            </button>
          </PopoverHandler>
          <PopoverContent className="rounded-3xl">
            <div>
              <ul className="p-2">
                <li className="hover:font-semibold text-gray-800 pb-2">
                  Profile
                </li>
                <li className="hover:font-semibold text-gray-800">
                  Sign In / Create Account
                </li>
              </ul>
            </div>
          </PopoverContent>
        </Popover>
      </div>
    </div>
  );
}
